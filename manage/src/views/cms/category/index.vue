<template>
  <div class="cms-category-container layout-padding">
    <div class="cms-category-padding layout-padding-auto layout-padding-view">
      <div class="cms-category-search mb15">
        <el-input v-model="state.tableData.param.search" size="default" placeholder="请输入分类名称"
                  style="max-width: 180px"></el-input>
        <el-button size="default" type="primary" class="ml10" @click="onSearch">
          <el-icon>
            <ele-Search/>
          </el-icon>
          查询
        </el-button>
        <el-button size="default" type="success" class="ml10" @click="onOpenAddCategory('add')">
          <el-icon>
            <ele-FolderAdd/>
          </el-icon>
          新增分类
        </el-button>
      </div>
      <el-table :data="state.tableData.data" v-loading="state.tableData.loading" style="width: 100%">
        <el-table-column type="index" label="序号" width="60"/>
        <el-table-column prop="thumbnail" label="特色图片" show-overflow-tooltip>
          <template #default="scope">
            <el-image style="width: 50px; height: 50px" :src="scope.row.thumbnail" fit="cover" >
              <template #error>
                <div class="image-slot">
                  <el-icon><icon-picture /></el-icon>
                </div>
              </template>
            </el-image>
          </template>
        </el-table-column>
        <el-table-column prop="name" label="分类名称" show-overflow-tooltip></el-table-column>
        <el-table-column prop="description" label="分类描述" show-overflow-tooltip></el-table-column>
        <el-table-column prop="postCount" label="文章数量" show-overflow-tooltip></el-table-column>
        <el-table-column prop="sort" label="排序" show-overflow-tooltip></el-table-column>
        <el-table-column label="操作" width="100">
          <template #default="scope">
            <el-button size="small" text type="primary" @click="onOpenEditCategory('edit', scope.row)" >修改</el-button>
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
    </div>
    <CategoryDialog ref="categoryDialogRef" @refresh="getTableData()"/>
  </div>
</template>

<script setup lang="ts" name="cmsCategory">

import {defineAsyncComponent, onMounted, reactive, ref} from "vue";
import {useCategoryApi} from "/@/api/cms/category";
import {ElMessage, ElMessageBox} from "element-plus";
import { Picture as IconPicture } from '@element-plus/icons-vue'

const categoryApi = useCategoryApi()
// 引入组件
const CategoryDialog = defineAsyncComponent(() => import('./dialog.vue'))

// 定义变量内容
const categoryDialogRef = ref();
const state = reactive<CmsCategoryState>({
  tableData: {
    param: {
      pageNo: 1,
      pageSize: 10,
    },
    data: [],
    total: 0,
    loading: false
  }
})

// 初始化表格数据
const getTableData = async () => {
  state.tableData.loading = true;
  const {records, total} = await categoryApi.findPage(state.tableData.param).finally(()=>{
    state.tableData.loading = false;
  });
  state.tableData.data = records;
  state.tableData.total = total;
}
const onSearch = () => {
  state.tableData.param.pageNo = 1;
  getTableData();
};
// 打开新增分类弹窗
const onOpenAddCategory = (type: string) => {
  categoryDialogRef.value?.openDialog(type)
}
// 打开编辑分类弹窗
const onOpenEditCategory = (type: string, row: RowCategoryType) => {
  categoryDialogRef.value?.openDialog(type, row)
}
// 删除分类
const onRowDel = (row: RowCategoryType) => {
  ElMessageBox.confirm(`此操作将永久删除分类：“${row.name}”，是否继续?`, '提示', {
    confirmButtonText: '确认',
    cancelButtonText: '取消',
    type: 'warning',
  }).then(async () => {
    await categoryApi.delete(row.categoryId);
    await getTableData();
    ElMessage.success('删除成功');
  }).catch(() => {
  });
}

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

<style scoped lang="scss">
.cms-category-container {
  .cms-category-padding {
    padding: 15px;
    .el-table {
      flex: 1;
    }
  }

  .image-slot {
    display: flex;
    justify-content: center;
    align-items: center;
    width: 100%;
    height: 100%;
    background: var(--el-fill-color-light);
    color: var(--el-text-color-secondary);
    font-size: 30px;
  }
  .image-slot .el-icon {
    font-size: 30px;
  }
}
</style>
