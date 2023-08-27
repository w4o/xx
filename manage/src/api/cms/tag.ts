import request from '/@/utils/request';

export function useTagApi() {
    return {
        findPage: (params?: object) => {
            return request({
                url: '/cms/tag',
                method: 'get',
                params
            })
        },
        add: (data?: object) => {
            return request({
                url: '/cms/tag',
                method: 'post',
                data
            })
        },
        update: (id: string, data?: object) => {
            return request({
                url: `/cms/tag/${id}`,
                method: 'put',
                data
            })
        },
        delete: (id: string) => {
            return request({
                url: `/cms/tag/${id}`,
                method: 'delete'
            })
        },
        getTag: (name: string) => {
            return request({
                url: `/cms/tag/name/${name}`,
                method: 'get'
            })
        }
    }
}
