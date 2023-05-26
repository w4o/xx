<template>
    <div class="system-dic-container layout-padding">
        <el-card shadow="hover" class="layout-padding-auto">
            <div class="system-user-search mb15">
                <el-input size="default" placeholder="请输入配置名称" style="max-width: 180px"> </el-input>
                <el-button size="default" type="primary" class="ml10" @click="doSearch">
                    <el-icon>
                        <ele-Search />
                    </el-icon>
                    查询
                </el-button>
                <el-button size="default" type="success" class="ml10" @click="onOpenAddConfig('add')">
                    <el-icon>
                        <ele-FolderAdd />
                    </el-icon>
                    新增配置
                </el-button>
            </div>
            <el-table :data="state.tableData.data" v-loading="state.tableData.loading" style="width: 100%">
                <el-table-column type="index" label="序号" width="60" />
                <el-table-column prop="configKey" label="配置名称" show-overflow-tooltip="true"></el-table-column>
                <el-table-column prop="configValue" label="配置值" show-overflow-tooltip></el-table-column>
                <el-table-column prop="remark" label="配置描述" show-overflow-tooltip></el-table-column>
                <el-table-column prop="createTime" label="创建时间" show-overflow-tooltip></el-table-column>
                <el-table-column label="操作" width="100">
                    <template #default="scope">
                        <el-button size="small" text type="primary" @click="onOpenEditConfig('edit', scope.row)">修改</el-button>
                        <el-button size="small" text type="primary" @click="onRowDel(scope.row)">删除</el-button>
                    </template>
                </el-table-column>
            </el-table>
            <el-pagination
                    @size-change="onHandleSizeChange"
                    @current-change="onHandleCurrentChange"
                    class="mt15"
                    :pager-count="5"
                    :page-sizes="[10, 20, 30]"
                    v-model:current-page="state.tableData.param.pageNo"
                    background
                    v-model:page-size="state.tableData.param.pageSize"
                    layout="total, sizes, prev, pager, next, jumper"
                    :total="state.tableData.total"
            >
            </el-pagination>
        </el-card>
        <ConfigDialog ref="configDialogRef" @refresh="getTableData()" />
    </div>
</template>

<script setup lang="ts" name="systemConfig">
import { defineAsyncComponent, reactive, onMounted, ref } from 'vue';
import { ElMessageBox, ElMessage } from 'element-plus';
import {useConfigApi} from '/@/api/system/config';

// 引入组件
const ConfigDialog = defineAsyncComponent(() => import('/@/views/system/config/dialog.vue'));

const configApi = useConfigApi()

// 定义变量内容
const dicDialogRef = ref();
const state = reactive<SysDicState>({
    tableData: {
        data: [],
        total: 0,
        loading: false,
        param: {
            pageNo: 1,
            pageSize: 10,
        },
    },
});

// 初始化表格数据
const getTableData = async () => {
    state.tableData.loading = true;
    const {records, total} = await configApi.findPage(state.tableData.param).finally(() => {
        state.tableData.loading = false;
    })
    state.tableData.data = records;
    state.tableData.total = total;
};

const doSearch = () => {
    state.tableData.param.pageNo = 1;
    getTableData();
};

// 打开新增配置弹窗
const onOpenAddConfig = (type: string) => {
    dicDialogRef.value.openDialog(type);
};
// 打开修改配置弹窗
const onOpenEditConfig = (type: string, row: RowDicType) => {
    dicDialogRef.value.openDialog(type, row);
};
// 删除配置
const onRowDel = (row: RowDicType) => {
    ElMessageBox.confirm(`此操作将永久删除配置名称：“${row.dicName}”，是否继续?`, '提示', {
        confirmButtonText: '确认',
        cancelButtonText: '取消',
        type: 'warning',
    })
        .then(() => {
            getTableData();
            ElMessage.success('删除成功');
        })
        .catch(() => {});
};
// 分页改变
const onHandleSizeChange = (val: number) => {
    state.tableData.param.pageSize = val;
    getTableData();
};
// 分页改变
const onHandleCurrentChange = (val: number) => {
    state.tableData.param.pageNo = val;
    getTableData();
};
// 页面加载时
onMounted(() => {
    getTableData();
});
</script>
