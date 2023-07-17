import { HttpEnum } from '@/enums/httpEnum'

import { BASE_URL, CODE_NAME, DATA_NAME, MESSAGE_NAME } from '@/config/apiConfig'

interface Options {
  url: string
  method: any
  data?: any
  header?: any
  timeout?: number
}
export const request = (options: Options) => {
  const { url, method, data, timeout } = options
  let { header } = options
  if (!header) {
    header = {
      'content-type': 'application/json',
    }
    if (uni.getStorageSync(ACCESS_TOKEN))
      header.Authorization = uni.getStorageSync(ACCESS_TOKEN)
  }

  return new Promise((resolve, reject) => {
    uni.request({
      url: BASE_URL + url,
      method: method || 'GET',
      data,
      header,
      timeout: timeout || 60000,
      success: (res: any) => {
        if (HttpEnum.SUCCESS.includes(res.data[CODE_NAME])) {
          resolve(res.data[DATA_NAME])
        }
        else if (HttpEnum.ACCOUNT_ERROR.includes(res.data[CODE_NAME])) {
          uni.showToast({
            title: '账号密码错误',
            icon: 'none',
            duration: 2000,
          })
          reject(res.data)
        }
        else if (HttpEnum.TIMEOUT.includes(res.data[CODE_NAME])) {
          uni.showToast({
            title: '登录失效，请重新登录',
            icon: 'none',
            duration: 2000,
          })
          uni.clearStorageSync()
          reject(res.data)
        }
        else {
          uni.showToast({
            title: res[MESSAGE_NAME],
            icon: 'none',
            duration: 2000,
          })
          reject(res.data)
        }
      },
      fail: (err) => {
        reject(err)
      },
    })
  })
}
