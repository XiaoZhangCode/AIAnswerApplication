import {AtButton} from "taro-ui";
import {Image, View} from "@tarojs/components";
import Taro from "@tarojs/taro";
import backgroundImg from "../../assets/bg.jpg";
import './index.scss'

export default () => {

  return (
    <View className="indexPage">
      <View className="at-article__h1 title">解锁MBTI 探索你的独特性格</View>
      <View className="at-article__h2 subTitle">
        开启心灵之旅，MBTI性格测试，解码你的独特魅力与天赋
      </View>
      <AtButton
        type="primary"
        circle
        className="enterBtn"
        onClick={() => {
          Taro.navigateTo({
            url: "/pages/doQuestion/index",
          });
        }}
      >
        开始测试
      </AtButton>
      <Image src={backgroundImg} className='background' />
    </View>
  )
}

