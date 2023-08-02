import request from '/@/utils/request';

export function useDeptApi() {
    return {
        add: (data: object) => {
            return request({
                url: '/sys/dept',
                method: 'post',
                data,
            });
        },
        update: (id: string, data?: object) => {
            return request({
                url: `/sys/dept/${id}`,
                method: 'put',
                data
            })
        },
        delete: (id: string) => {
            return request({
                url: `/sys/dept/${id}`,
                method: 'delete'
            })
        },
        tree: ()=>{
            return request({
                url: '/sys/dept/tree',
                method: 'get'
            })
        }
    }
}
