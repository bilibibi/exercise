package main

import (
	"bytes"
	"fmt"
	"image/png"
	"io/ioutil"
	"net/http"
	"os"
)

func main() {
	fontResp, err := http.Get("https://s4s-datas.oss-cn-shanghai.aliyuncs.com/fr/YaHei.ttf")
	if err != nil {
		fmt.Println("没有找到二维码字体文件, err: ", err)
		os.Exit(1)
	}
	defer fontResp.Body.Close()

	fontBytes, err := ioutil.ReadAll(fontResp.Body)
	if err != nil {
		fmt.Println("二维码字体文件读取失败, err: ", err)
		os.Exit(1)
	}

	// gTrueFont, err = freetype.ParseFont(fontBytes)
	// if err != nil {
	// 	beego.Error("fontBytes", string(fontBytes))
	// 	beego.Error("二维码字体文件解析失败, err: ", err)
	// 	os.Exit(1)
	// }

	tplResp, err := http.Get("https://s4s-datas.oss-cn-shanghai.aliyuncs.com/fr/qrcode_tpl_fast_repair.png")
	if err != nil {
		// handle error
		fmt.Println("没有找到二维码模板文件, err: ", err)
		os.Exit(1)
	}
	defer tplResp.Body.Close()

	tplBytes, err := ioutil.ReadAll(tplResp.Body)
	if err != nil {
		fmt.Println("二维码模板文件读取失败, err: ", err)
		os.Exit(1)
	}

	frQrTpl, err := png.Decode(bytes.NewReader(tplBytes))
	if err != nil {
		fmt.Println("tplBytes", string(tplBytes))
		fmt.Println("二维码字体模板文件解析失败, err: ", err)
		os.Exit(1)
	}

}
