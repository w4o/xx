import request from '/@/utils/request';


export function mediaApi() {
    return {
        /**
         * @description: 分页查询媒体
         */
        getMedia: (params: any) => {
            return request({
                url: '/sys/media',
                method: 'get',
                params
            })
        },
        /**
         * @description: 删除
         */
        deleteMedia: (id: any) => {
            return request({
                url: `/sys/media/${id}`,
                method: 'delete',
            })
        },
        /**
         * @description: 修改
         */
        updateMedia: (data: any) => {
            return request({
                url: `/sys/media/${data.mediaId}`,
                method: 'put',
                data
            })
        }
    }
}
