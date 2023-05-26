import request from '/@/utils/request';


export function useDictApi() {
    return {
        findPage: (params?: object) => {
            return request({
                url: '/sys/dictType',
                method: 'get',
                params
            })
        }
    }
}
