<template>
	<div class="system-dict-data-container layout-padding">
		<el-card shadow="hover" class="layout-padding-auto">
			<div class="system-dict-data-header mb15">
				<el-page-header :content="'【' + state.header.title + '】字典详细'" @back="goBack" />
			</div>
			<div class="system-dict-data-search mb15">
				<el-input size="default" placeholder="请输入字典名称" style="max-width: 180px"> </el-input>
				<el-button size="default" type="primary" class="ml10" @click="doSearch" :icon="Search">查询</el-button>
				<el-button size="default" type="success" class="ml10" @click="onOpenAddDic('add')" :icon="Plus">新增数据</el-button>
			</div>
			<el-table :data="state.tableData.data" v-loading="state.tableData.loading" style="width: 100%">
				<el-table-column type="index" label="序号" width="60" />
				<el-table-column prop="name" label="字典名称" show-overflow-tooltip></el-table-column>
				<el-table-column prop="label" label="字段名" show-overflow-tooltip></el-table-column>
				<el-table-column prop="enabled" label="字典状态" show-overflow-tooltip>
					<template #default="scope">
						<el-tag type="success" v-if="scope.row.status">启用</el-tag>
						<el-tag type="info" v-else>禁用</el-tag>
					</template>
				</el-table-column>
				<el-table-column prop="remark" label="字典描述" show-overflow-tooltip></el-table-column>
				<el-table-column prop="createTime" label="创建时间" show-overflow-tooltip></el-table-column>
				<el-table-column label="操作" width="130">
					<template #default="scope">
						<el-button size="small" text type="primary" @click="onOpenView(scope.row)">查看</el-button>
						<el-button size="small" text type="primary" @click="onOpenEditDic('edit', scope.row)">修改</el-button>
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
		<DicDialog ref="dicDialogRef" @refresh="getTableData()" />
	</div>
</template>

<script setup lang="ts" name="systemDic">
import { defineAsyncComponent, reactive, onMounted, ref } from 'vue';
import { ElMessageBox, ElMessage } from 'element-plus';
import { useDictApi } from '/@/api/system/dict';
import router from '/@/router';
import { useDictDataApi } from '/@/api/system/dictData';
import { Plus, Search } from '@element-plus/icons-vue';

// 引入组件
const DicDialog = defineAsyncComponent(() => import('/@/views/system/dic/dialog.vue'));

const dictDataApi = useDictDataApi();

// 定义变量内容
const dicDialogRef = ref();
const state = reactive({
	header: {
		title: '' as string,
	},
	tableData: {
		data: [],
		total: 0,
		loading: false,
		param: {
			dictTypeId: '',
			pageNo: 1,
			pageSize: 10,
		},
	},
});

// 初始化表格数据
const getTableData = async () => {
	state.tableData.loading = true;
	const { records, total } = await dictDataApi.findPage(state.tableData.param).finally(() => {
		state.tableData.loading = false;
	});
	state.tableData.data = records;
	state.tableData.total = total;
};

const doSearch = () => {
	state.tableData.param.pageNo = 1;
	getTableData();
};

// 打开新增字典弹窗
const onOpenAddDic = (type: string) => {
	dicDialogRef.value.openDialog(type);
};
// 打开修改字典弹窗
const onOpenEditDic = (type: string, row: RowDicType) => {
	dicDialogRef.value.openDialog(type, row);
};

const onOpenView = (row: any) => {};
// 删除字典
const onRowDel = (row: RowDicType) => {
	ElMessageBox.confirm(`此操作将永久删除字典名称：“${row.dicName}”，是否继续?`, '提示', {
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

const goBack = async () => {
	await router.push('/system/dict');
};
// 页面加载时
onMounted(() => {
	state.header.title = router.currentRoute.value.query.name + '';
	state.tableData.param.dictTypeId = router.currentRoute.value.query.id + '';
	getTableData();
});
</script>
