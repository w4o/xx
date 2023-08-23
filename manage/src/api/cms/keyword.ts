import request from '/@/utils/request';

export function useKeywordApi() {
    return {
        findPage: (params?: object) => {
            return request({
                url: '/cms/keyword',
                method: 'get',
                params
            })
        },
        add: (data?: object) => {
            return request({
                url: '/cms/keyword',
                method: 'post',
                data
            })
        },
        update: (id: string, data?: object) => {
            return request({
                url: `/cms/keyword/${id}`,
                method: 'put',
                data
            })
        },
        delete: (id: string) => {
            return request({
                url: `/cms/keyword/${id}`,
                method: 'delete'
            })
        }
    }
}
