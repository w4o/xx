import request from '/@/utils/request';

export function useCategoryApi() {
    return {
        findPage: (params?: object) => {
            return request({
                url: '/cms/category',
                method: 'get',
                params
            })
        },
        add: (data?: object) => {
            return request({
                url: '/cms/category',
                method: 'post',
                data
            })
        },
        update: (id: string, data?: object) => {
            return request({
                url: `/cms/category/${id}`,
                method: 'put',
                data
            })
        },
        delete: (id: string) => {
            return request({
                url: `/cms/category/${id}`,
                method: 'delete'
            })
        },
        findTree: () => {
            return request({
                url: '/cms/category/tree',
                method: 'get'
            })
        }
    }
}
