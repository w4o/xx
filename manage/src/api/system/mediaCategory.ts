import request from '/@/utils/request';


export function mediaCategoryApi() {
    return {
        /**
         * @description: 查询媒体分类树
         */
        getMediaCategory: () => {
            return request({
                url: '/sys/media-category/tree',
                method: 'get',
            })
        },
        /**
         * @description: 添加媒体分类
         */
        mediaCategory: (data: any) => {
            return request({
                url: '/sys/media-category',
                method: 'post',
                data
            })
        },
        /**
         * @description: 删除媒体分类
         */
        deleteMediaCategory: (id: number) => {
            return request({
                url: `/sys/media-category/${id}`,
                method: 'delete',
            })
        },
        /**
         * @description: 修改媒体分类
         */
        updateMediaCategory: (data: any) => {
            return request({
                url: `/sys/media-category/${data.id}`,
                method: 'put',
                data
            })
        }
    }
}
