import request from '/@/utils/request';


export function useRoleApi() {
    return {
        findPage: (params?: object) => {
            return request({
                url: '/sys/role',
                method: 'get',
                params
            })
        },
        add: (data?: object) => {
            return request({
                url: '/sys/role',
                method: 'post',
                data
            })
        },
        update: (id: string, data?: object) => {
            return request({
                url: `/sys/role/${id}`,
                method: 'put',
                data
            })
        },
        delete: (id: string) => {
            return request({
                url: `/sys/role/${id}`,
                method: 'delete'
            })
        },
        option: () => {
            return request({
                url: `/sys/role/option`,
                method: 'get'
            })
        }
    }
}
