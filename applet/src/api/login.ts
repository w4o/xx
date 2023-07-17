import { request } from '@/utils/http'

export function login(data: any) {
  return request({
    url: '/login',
    method: 'post',
    data,
  })
}
