import request from '/@/utils/request';

export function usePostApi() {
    return {
        findPage: (params?: object) => {
            return request({
                url: '/cms/post',
                method: 'get',
                params
            })
        },
        add: (data?: object) => {
            return request({
                url: '/cms/post',
                method: 'post',
                data
            })
        },
        update: (data?: any) => {
            return request({
                url: `/cms/post/${data?.id}`,
                method: 'put',
                data
            })
        },
        delete: (id: string) => {
            return request({
                url: `/cms/post/${id}`,
                method: 'delete'
            })
        },
        getPost: (id: string) => {
            return request({
                url: `/cms/post/${id}`,
                method: 'get'
            })
        },
        draft: (id: string) => {
            return request({
                url: `/cms/post/${id}/draft`,
                method: 'put'
            })
        },
        publish: (id: string) => {
            return request({
                url: `/cms/post/${id}/publish`,
                method: 'put'
            })
        }
    }
}
