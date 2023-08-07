import request from '/@/utils/request';


export function uploadApi() {
    return {
        /**
         * @description: 分页查询媒体
         */
        uploadImage: (data) => {
            return request({
                url: '/upload/image',
                method: 'post',
                data,
                headers: {
                    'Content-Type': 'multipart/form-data'
                }
            })
        },
    }
}
