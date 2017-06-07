package com.platform.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.platform.dto.Exposer;
import com.platform.dto.PurchaseExec;
import com.platform.dto.PurchaseResult;
import com.platform.dto.PurchaseStatEnum;
import com.platform.entity.Goods;
import com.platform.execption.PurchaseCloseException;
import com.platform.execption.RepeatPurchaseException;
import com.platform.service.GoodsService;

@Controller
@RequestMapping("/purchase")
public class PurchaseController {

	@Autowired
	GoodsService goodsService;

	// TODO: 当使用@RestController时，这里页面只展示了list字符串。。。
	// rest是普通controller和responseBody的结合体
	@GetMapping("/list")
	public String list(Model model) {
		List<Goods> goods = goodsService.getGoodsList();
		model.addAttribute("list", goods);
		return "list";
	}

	@GetMapping(value = "/{goodsid}/detail")
	public String detail(@PathVariable("goodsid") Long goodsid, Model model) {
		if (goodsid == null) {
			System.out.println("==err not goods id");
			return "redirect:/purchase/list";
		}

		Goods goods = goodsService.getById(goodsid);
		if (goods == null) {
			System.out.println("==err not goods");
			return "forward:/purchase/list";
		}

		model.addAttribute("goods", goods);

		return "detail";
	}

	// ajax ,json暴露秒杀接口的方法
	@PostMapping(value = "/{goodsid}/exposer", produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public PurchaseResult<Exposer> exposer(@PathVariable("goodsid")Long goodsid) {
		PurchaseResult<Exposer> result;
		try {
			Exposer exposer = goodsService.exportGoodsUrl(goodsid);
			result = new PurchaseResult<Exposer>(true, exposer);
		} catch (Exception e) {
			e.printStackTrace();
			result = new PurchaseResult<Exposer>(false, e.getMessage());
		}

		return result;
	}

	@PostMapping(value = "/{goodsid}/{md5}/execution", produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public PurchaseResult<PurchaseExec> execute(@PathVariable("goodsid") Long goodsid, @PathVariable("md5") String md5,
			@CookieValue(value = "killPhone", required = false) Long phone) {
		if (phone == null) {
			return new PurchaseResult<PurchaseExec>(false, "未注册");
		}
		//PurchaseResult<PurchaseExec> result;

		try {
			PurchaseExec execution = goodsService.executePurchase(goodsid, phone, md5);
			return new PurchaseResult<PurchaseExec>(true, execution);
		} catch (RepeatPurchaseException e1) {
			PurchaseExec execution = new PurchaseExec(goodsid, PurchaseStatEnum.REPEAT_PURCHASE);
			return new PurchaseResult<PurchaseExec>(false, execution);
		} catch (PurchaseCloseException e2) {
			PurchaseExec execution = new PurchaseExec(goodsid, PurchaseStatEnum.END);
			return new PurchaseResult<PurchaseExec>(false, execution);
		} catch (Exception e) {
			PurchaseExec execution = new PurchaseExec(goodsid, PurchaseStatEnum.INNER_ERROR);
			return new PurchaseResult<PurchaseExec>(false, execution);
		}

	}

	// 获取系统时间
	@GetMapping("/time/now")
	@ResponseBody
	public PurchaseResult<Long> time() {
		Date now = new Date();
		return new PurchaseResult<Long>(true, now.getTime());
	}
}
