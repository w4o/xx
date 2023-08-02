<template>
	<div class="system-dept-container layout-padding">
		<el-card shadow="hover" class="layout-padding-auto">
			<div class="system-dept-search mb15">
				<el-button size="default" type="success" class="ml10" @click="onOpenAddDept('add','0')">
					<el-icon>
						<ele-FolderAdd />
					</el-icon>
					新增部门
				</el-button>
			</div>
			<el-table
				:data="state.tableData.data"
				v-loading="state.tableData.loading"
				style="width: 100%"
				row-key="id"
				default-expand-all
				:tree-props="{ children: 'children', hasChildren: 'hasChildren' }"
			>
				<el-table-column prop="deptName" label="部门名称" show-overflow-tooltip> </el-table-column>
				<el-table-column prop="sort" label="排序" show-overflow-tooltip width="80" />
				<el-table-column prop="enabled" label="部门状态" show-overflow-tooltip>
					<template #default="scope">
						<el-tag type="success" v-if="scope.row.enabled">启用</el-tag>
						<el-tag type="info" v-else>禁用</el-tag>
					</template>
				</el-table-column>
				<el-table-column prop="description" label="部门描述" show-overflow-tooltip></el-table-column>
				<el-table-column prop="createTime" label="创建时间" show-overflow-tooltip></el-table-column>
				<el-table-column label="操作" show-overflow-tooltip width="140">
					<template #default="scope">
						<el-button size="small" text type="primary" @click="onOpenAddDept('add', scope.row.id)">新增</el-button>
						<el-button size="small" text type="primary" @click="onOpenEditDept('edit', scope.row)">修改</el-button>
						<el-button size="small" text type="primary" @click="onTableRowDel(scope.row)">删除</el-button>
					</template>
				</el-table-column>
			</el-table>
		</el-card>
		<DeptDialog ref="deptDialogRef" @refresh="getTableData()" />
	</div>
</template>

<script setup lang="ts" name="systemDept">
import {defineAsyncComponent, onMounted, reactive, ref} from 'vue';
import {ElMessage, ElMessageBox} from 'element-plus';
import {useDeptApi} from "/@/api/system/dept";

// 引入组件
const DeptDialog = defineAsyncComponent(() => import('/@/views/system/dept/dialog.vue'));

// 定义变量内容
const deptDialogRef = ref();
const deptApi = useDeptApi();

const state = reactive({
	tableData: {
		data: [] as any,
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
  state.tableData.data = await deptApi.tree();
  state.tableData.total = state.tableData.data.length;
  state.tableData.loading = false;
};
// 打开新增菜单弹窗
const onOpenAddDept = (type: string, parentId: string) => {
	deptDialogRef.value.openDialog(type, {parentId});
};
// 打开编辑菜单弹窗
const onOpenEditDept = (type: string, row: DeptTreeType) => {
	deptDialogRef.value.openDialog(type, row);
};
// 删除当前行
const onTableRowDel = (row: DeptTreeType) => {
	ElMessageBox.confirm(`此操作将永久删除部门：${row.deptName}, 是否继续?`, '提示', {
		confirmButtonText: '删除',
		cancelButtonText: '取消',
		type: 'warning',
	})
		.then(async () => {
      await deptApi.delete(row.id);
      ElMessage.success('删除成功');
      await getTableData();
    })
		.catch(() => {});
};
// 页面加载时
onMounted(() => {
	getTableData();
});
</script>
