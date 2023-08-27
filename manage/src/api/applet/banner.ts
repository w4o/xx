import request from '/@/utils/request';

export function useAppletBannerApi() {
    return {
        findPage: (params?: object) => {
            return request({
                url: '/applet/banner',
                method: 'get',
                params
            })
        },
        add: (data?: object) => {
            return request({
                url: '/applet/banner',
                method: 'post',
                data
            })
        },
        update: (data?: any) => {
            return request({
                url: `/applet/banner/${data.bannerId}`,
                method: 'put',
                data
            })
        },
        delete: (id: string) => {
            return request({
                url: `/applet/banner/${id}`,
                method: 'delete'
            })
        },
        invisible: (id: string) => {
            return request({
                url: `/applet/banner/${id}/invisible`,
                method: 'put'
            })
        },
        visible: (id: string) => {
            return request({
                url: `/applet/banner/${id}/visible`,
                method: 'put'
            })
        }
    }
}
