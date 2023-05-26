import request from '/@/utils/request';


export function useUserApi() {
    return {
        findPage: (params?: object) => {
            return request({
                url: '/sys/user',
                method: 'get',
                params
            })
        }
    }
}
