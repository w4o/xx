import request from '/@/utils/request';


export function useMenuApi() {
    return {
        getTreeOption: (params?: object) => {
            return request({
                url: '/sys/menu/treeOption',
                method: 'get',
                params
            })
        },
        getMenuTree: (params?: object) => {
            return request({
                url: '/sys/menu/tree',
                method: 'get',
                params
            })
        },
        /**
         * 编辑菜单
         * @param data
         */
        editMenu: (data?: any) => {
            return request({
                url: `/sys/menu/${data?.id}`,
                method: 'put',
                data
            })
        },
        /**
         * 增加菜单
         */
        addMenu: (data?: any) => {
            return request({
                url: '/sys/menu',
                method: 'post',
                data
            })
        },
        /**
         * 删除菜单
         */
        deleteMenu: (id?: string) => {
            return request({
                url: `/sys/menu/${id}`,
                method: 'delete'
            })
        }
    }
}
