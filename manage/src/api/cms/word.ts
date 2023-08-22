import request from '/@/utils/request';

export function useWordApi() {
    return {
        findPage: (params?: object) => {
            return request({
                url: '/cms/word',
                method: 'get',
                params
            })
        },
        add: (data?: object) => {
            return request({
                url: '/cms/word',
                method: 'post',
                data
            })
        },
        update: (id: string, data?: object) => {
            return request({
                url: `/cms/word/${id}`,
                method: 'put',
                data
            })
        },
        delete: (id: string) => {
            return request({
                url: `/cms/word/${id}`,
                method: 'delete'
            })
        }
    }
}
