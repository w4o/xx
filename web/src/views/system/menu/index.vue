<template>
    <div class="system-menu-container layout-pd">
        <el-card shadow="hover">
            <div class="system-menu-search mb15">
                <el-input size="default" v-model="state.tableData.param.search" placeholder="请输入菜单名称"
                          style="max-width: 180px"></el-input>
                <el-button size="default" type="primary" class="ml10" @click="doSearch">
                    <el-icon>
                        <ele-Search/>
                    </el-icon>
                    查询
                </el-button>
                <el-button size="default" type="success" class="ml10" @click="onOpenAddMenu('add')">
                    <el-icon>
                        <ele-FolderAdd/>
                    </el-icon>
                    新增菜单
                </el-button>
            </div>
            <el-table
                    :data="state.tableData.data"
                    v-loading="state.tableData.loading"
                    style="width: 100%"
                    row-key="path"
                    :tree-props="{ children: 'children', hasChildren: 'hasChildren' }"
            >
                <el-table-column label="菜单名称" show-overflow-tooltip>
                    <template #default="scope">
                        <SvgIcon :name="scope.row.icon"/>
                        <span class="ml10">{{ $t(scope.row.title) }}</span>
                    </template>
                </el-table-column>
                <el-table-column prop="path" label="路由路径" show-overflow-tooltip></el-table-column>
                <el-table-column label="组件路径" show-overflow-tooltip>
                    <template #default="scope">
                        <span>{{ scope.row.component }}</span>
                    </template>
                </el-table-column>
                <el-table-column label="权限标识" show-overflow-tooltip>
                    <template #default="scope">
                        <span>{{ scope.row.roles }}</span>
                    </template>
                </el-table-column>
                <el-table-column label="排序" show-overflow-tooltip width="80">
                    <template #default="scope">
                        {{ scope.row.sort }}
                    </template>
                </el-table-column>
                <el-table-column label="类型" show-overflow-tooltip width="80">
                    <template #default="scope">
                        <el-tag type="success" size="small">{{ scope.row.xx }}菜单</el-tag>
                    </template>
                </el-table-column>
                <el-table-column label="操作" show-overflow-tooltip width="140">
                    <template #default="scope">
                        <el-button size="small" text type="primary" @click="onOpenAddMenu('add', scope.row)">新增
                        </el-button>
                        <el-button size="small" text type="primary" @click="onOpenEditMenu('edit', scope.row)">修改
                        </el-button>
                        <el-button size="small" text type="primary" @click="onTabelRowDel(scope.row)">删除</el-button>
                    </template>
                </el-table-column>
            </el-table>
        </el-card>
        <MenuDialog ref="menuDialogRef" @refresh="getTableData()"/>
    </div>
</template>

<script setup lang="ts" name="systemMenu">
import {defineAsyncComponent, ref, onMounted, reactive} from 'vue';
import {RouteRecordRaw} from 'vue-router';
import {ElMessageBox, ElMessage} from 'element-plus';
import {useMenuApi} from '/@/api/system/menu';
import {setBackEndControlRefreshRoutes} from "/@/router/backEnd";

// 引入组件
const MenuDialog = defineAsyncComponent(() => import('/@/views/system/menu/dialog.vue'));

const menuApi = useMenuApi();

// 定义变量内容
const menuDialogRef = ref();
const state = reactive({
    tableData: {
        data: [] as any,
        loading: true,
        param: {
            search: '',
        }
    },
});

// 获取路由数据，真实请从接口获取
const getTableData = async () => {
    state.tableData.loading = true;
    state.tableData.data = await menuApi.getMenuTree(state.tableData.param).finally(() => {
        state.tableData.loading = false;
    });
};

const doSearch = () => {
    getTableData();
};
// 打开新增菜单弹窗
const onOpenAddMenu = (type: string, row?) => {
    menuDialogRef.value.openDialog(type, row);
};
// 打开编辑菜单弹窗
const onOpenEditMenu = (type: string, row: RouteRecordRaw) => {
    menuDialogRef.value.openDialog(type, row);
};
// 删除当前行
const onTabelRowDel = (row) => {
    ElMessageBox.confirm(`此操作将永久删除路由：${row.title}, 是否继续?`, '提示', {
        confirmButtonText: '删除',
        cancelButtonText: '取消',
        type: 'warning',
    })
        .then(async () => {
            await menuApi.deleteMenu(row.id)
            ElMessage.success('删除成功');
            await getTableData();
            await setBackEndControlRefreshRoutes() // 刷新菜单，未进行后端接口测试
        })
        .catch(() => {
        });
};
// 页面加载时
onMounted(() => {
    getTableData();
});
</script>
