package main

import (
	"fmt"
	"time"
)

func main() {
	time1, time2 := "2018-03-14 00:00:00", "2018-03-19 00:00:00"

	diff := getTimes(time1, time2, 1)

	fmt.Println(diff)
	for k, v := range diff {
		fmt.Println(k, v)
	}

}

//获取相差时间数组
func getTimes(startTime, endTime string, statsType int) (times []string) {
	st, err := time.ParseInLocation("2006-01-02 15:04:05", startTime, time.Local)
	et, err := time.ParseInLocation("2006-01-02 15:04:05", endTime, time.Local)

	if err == nil && st.Before(et) {
		sub := et.Sub(st)
		if statsType == 1 {
			diff := int(sub.Hours())
			for i := 1; i <= diff; i++ {
				h, _ := time.ParseDuration(fmt.Sprintf("%dh", i))
				times = append(times, st.Add(h).Format("2006-01-02 15"))
			}
		} else {
			diff := int(sub.Hours() / 24)
			for i := 1; i <= diff; i++ {
				d, _ := time.ParseDuration(fmt.Sprintf("%dh", i*24))
				times = append(times, st.Add(d).Format("2006-01-02"))
			}
		}
	}
	return
}
