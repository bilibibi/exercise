package main

import (
	"encoding/json"
	"fmt"
	"io/ioutil"
	"net/http"
	"os"
)

type Goods struct {
	Code interface{} `json:"code"`
	Data struct {
		CurUserType string `json:"curUserType"`
		GoodsList   []struct {
			ActivityGroupID interface{} `json:"activityGroupId"`
			ActivityID      interface{} `json:"activityId"`
			ActivityLink    struct {
				AppURL      string `json:"appURL"`
				DisPlayName string `json:"disPlayName"`
				WebURL      string `json:"webURL"`
			} `json:"activityLink"`
			ActivityLinks       interface{} `json:"activityLinks"`
			BatchBuyStepLength  int         `json:"batchBuyStepLength"`
			BatchPrice          interface{} `json:"batchPrice"`
			BatchPriceAmount    interface{} `json:"batchPriceAmount"`
			BatchStartAmount    int         `json:"batchStartAmount"`
			BrandName           string      `json:"brandName"`
			Cat1ID              int         `json:"cat1Id"`
			Cat1Name            string      `json:"cat1Name"`
			Cat2ID              int         `json:"cat2Id"`
			Cat2Name            string      `json:"cat2Name"`
			CatID               string      `json:"catId"`
			CatName             string      `json:"catName"`
			DelGoodsPrice       interface{} `json:"delGoodsPrice"`
			DelGoodsPriceAmount interface{} `json:"delGoodsPriceAmount"`
			DelGoodsPriceName   interface{} `json:"delGoodsPriceName"`
			DifferencePrice     interface{} `json:"differencePrice"`
			GoodsAttr           struct {
				Num472 string `json:"472"`
				Num473 string `json:"473"`
				Num521 string `json:"521"`
				Num524 string `json:"524"`
				Num555 string `json:"555"`
				Num556 string `json:"556"`
			} `json:"goodsAttr"`
			GoodsCarSearchList interface{} `json:"goodsCarSearchList"`
			GoodsDisplayName   string      `json:"goodsDisplayName"`
			GoodsFormat        string      `json:"goodsFormat"`
			GoodsID            int         `json:"goodsId"`
			GoodsImg           string      `json:"goodsImg"`
			GoodsName          string      `json:"goodsName"`
			GoodsNumber        int         `json:"goodsNumber"`
			GoodsPrice         interface{} `json:"goodsPrice"`
			GoodsSn            string      `json:"goodsSn"`
			GoodsTag           interface{} `json:"goodsTag"`
			HidePrice          string      `json:"hidePrice"`
			MeasureUnit        string      `json:"measureUnit"`
			MinMeasureUnit     string      `json:"minMeasureUnit"`
			MyGoods            interface{} `json:"myGoods"`
			OeNum              string      `json:"oeNum"`
			PackageFormat      string      `json:"packageFormat"`
			SellerID           int         `json:"sellerId"`
			SellerName         string      `json:"sellerName"`
			SellerNick         string      `json:"sellerNick"`
			ShippingFee        interface{} `json:"shippingFee"`
			ShippingFeeDesc    interface{} `json:"shippingFeeDesc"`
			ShippingFrom       interface{} `json:"shippingFrom"`
			StockType          int         `json:"stockType"`
			SubName            string      `json:"subName"`
			SuitCar            interface{} `json:"suitCar"`
			TagURL             string      `json:"tagUrl"`
			TagURLText         string      `json:"tagUrlText"`
		} `json:"goodsList"`
		TotalPages int `json:"totalPages"`
		UserID     int `json:"userId"`
	} `json:"data"`
	Message interface{} `json:"message"`
	Success bool        `json:"success"`
}

var maxLen int

func main() {
	//生成client 参数为默认
	client := &http.Client{}

	//生成要访问的url
	// url := "http://www.tqmall.com/Search/goods?catSecId=8&p=1"

	urls := []string{
		// "http://www.tqmall.com/Search/goods?catSecId=8&p=1", // 轮胎
		// "http://www.tqmall.com/Search/goods?catSecId=8&p=2", // 轮胎
		// "http://www.tqmall.com/Search/goods?catSecId=8&p=3", // 轮胎
		// "http://www.tqmall.com/Search/goods?catSecId=8&p=4", // 轮胎
		// "http://www.tqmall.com/Search/goods?catSecId=8&p=5", // 轮胎
		// "http://www.tqmall.com/Search/goods?catSecId=8&p=6", // 轮胎
		// "http://www.tqmall.com/Search/goods?catSecId=8&p=7", // 轮胎

		// "http://www.tqmall.com/Search/goods?q=&catSecId=6&p=1", // 汽机油
		// "http://www.tqmall.com/Search/goods?q=&catSecId=6&p=2", // 汽机油
		// "http://www.tqmall.com/Search/goods?q=&catSecId=6&p=3", // 汽机油
		// "http://www.tqmall.com/Search/goods?q=&catSecId=6&p=4", // 汽机油
		// "http://www.tqmall.com/Search/goods?q=&catSecId=6&p=5", // 汽机油

		// "http://www.tqmall.com/Search/goods?q=&catSecId=77", // 柴机油

		// "http://www.tqmall.com/Search/goods?q=&catSecId=98", // 齿轮油

		// "http://www.tqmall.com/Search/goods?q=&catSecId=3", // 自动变速箱油

		// "http://www.tqmall.com/Search/goods?q=&catSecId=10&p=1", // 设备
		// "http://www.tqmall.com/Search/goods?q=&catSecId=10&p=2", // 设备
		// "http://www.tqmall.com/Search/goods?q=&catSecId=10&p=3", // 设备

		// "http://www.tqmall.com/Search/goods?q=&catSecId=11&p=1", // 工具
		// "http://www.tqmall.com/Search/goods?q=&catSecId=11&p=2", // 工具

		// "http://www.tqmall.com/Search/goods?q=&catSecId=92", // 抽取机

		// "http://www.tqmall.com/Search/goods?q=&catSecId=93", // 加注机

		// "http://www.tqmall.com/Search/goods?q=&catSecId=94", // 四轮定位仪

		// "http://www.tqmall.com/Search/goods?q=&catSecId=95", // 大梁校正仪

		// "http://www.tqmall.com/Search/goods?q=&catSecId=96&p=1", // 举升机
		// "http://www.tqmall.com/Search/goods?q=&catSecId=96&p=2", // 举升机

		// "http://www.tqmall.com/Search/goods?q=&catSecId=97", // 清洗机

		// "http://www.tqmall.com/Search/goods?q=&catSecId=14&p=1", // 蓄电池
		// "http://www.tqmall.com/Search/goods?q=&catSecId=14&p=2", // 蓄电池
		// "http://www.tqmall.com/Search/goods?q=&catSecId=14&p=3", // 蓄电池

		"http://www.tqmall.com/Search/goods?q=&catSecId=26", // 清洗剂
	}

	maxLen = 0

	for _, url := range urls {
		//提交请求
		reqest, err := http.NewRequest("GET", url, nil)
		if err != nil {
			panic(err)
		}

		//处理返回结果
		response, _ := client.Do(reqest)
		//将结果定位到标准输出 也可以直接打印出来 或者定位到其他地方进行相应的处理
		// stdout := os.Stdout
		// _, err = io.Copy(stdout, response.Body)

		//返回的状态码
		status := response.StatusCode
		if status == 200 {
			body, _ := ioutil.ReadAll(response.Body)
			writeSqlToFile(body)
		} else {
			fmt.Println("请求失败")
		}
	}

	// fmt.Println(maxLen)
}

func writeSqlToFile(jsonResult []byte) {
	var r Goods
	json.Unmarshal(jsonResult, &r)
	for _, goods := range r.Data.GoodsList {
		sql := fmt.Sprintf("insert into `goods_standard` values(null,'%s','%s','%s',0,'%s','%s','%s','%s','',9,43,'',now(),null);\n",
			goods.GoodsSn,
			goods.GoodsName,
			goods.GoodsImg,
			goods.MeasureUnit,
			goods.PackageFormat,
			goods.BrandName,
			goods.GoodsFormat)
		// if len(goods.GoodsFormat) > maxLen {
		// 	maxLen = len(goods.GoodsFormat)
		// }

		// var d1 = []byte(sql)
		// ioutil.WriteFile("./test.txt", d1, 0666) //写入文件(字节数组)

		appendToFile("../../goods_standard.sql", sql)
	}
}

func appendToFile(fileName string, content string) error {
	// 以只写的模式，打开文件
	f, err := os.OpenFile(fileName, os.O_WRONLY, 0644)
	if err != nil {
		fmt.Println("cacheFileList.yml file create failed. err: " + err.Error())
	} else {
		// 查找文件末尾的偏移量
		n, _ := f.Seek(0, os.SEEK_END)
		// 从末尾的偏移量开始写入内容
		_, err = f.WriteAt([]byte(content), n)
	}
	defer f.Close()
	return err
}
