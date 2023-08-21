<template>
  <div class="cms-category-container layout-padding">
    <div class="cms-category-padding layout-padding-auto layout-padding-view">
      <div class="cms-category-search mb15">
        <el-input v-model="state.tableData.param.search" placeholder="请输入分类名称" size="default"
                  style="max-width: 180px"></el-input>
        <el-button class="ml10" size="default" type="primary" @click="onSearch">
          <el-icon>
            <ele-Search/>
          </el-icon>
          查询
        </el-button>
        <el-button class="ml10" size="default" type="success" @click="onOpenAddCategory('add')">
          <el-icon>
            <ele-FolderAdd/>
          </el-icon>
          新增分类
        </el-button>
      </div>
      <el-table v-loading="state.tableData.loading" :data="state.tableData.data" style="width: 100%">
        <el-table-column label="序号" type="index" width="60"/>
        <el-table-column label="特色图片" prop="thumbnail" show-overflow-tooltip>
          <template #default="scope">
            <el-image :src="scope.row.thumbnail" fit="cover" style="width: 50px; height: 50px">
              <template #error>
                <div class="image-slot">
                  <el-icon>
                    <icon-picture/>
                  </el-icon>
                </div>
              </template>
            </el-image>
          </template>
        </el-table-column>
        <el-table-column label="分类名称" prop="name" show-overflow-tooltip></el-table-column>
        <el-table-column label="分类描述" prop="description" show-overflow-tooltip></el-table-column>
        <el-table-column label="文章数量" prop="postCount" show-overflow-tooltip></el-table-column>
        <el-table-column label="操作" width="100">
          <template #default="scope">
            <el-button size="small" text type="primary" @click="onOpenEditCategory('edit', scope.row)">修改</el-button>
            <el-button size="small" text type="primary" @click="onRowDel(scope.row)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
      <el-pagination
          v-model:current-page="state.tableData.param.pageNo"
          v-model:page-size="state.tableData.param.pageSize"
          :page-sizes="[10, 20, 30]"
          :pager-count="5"
          :total="state.tableData.total"
          background
          class="mt15"
          layout="total, sizes, prev, pager, next, jumper"
          @size-change="onHandleSizeChange"
          @current-change="onHandleCurrentChange"
      >
      </el-pagination>
    </div>
    <CategoryDialog ref="categoryDialogRef" @refresh="getTableData()"/>
  </div>
</template>

<script lang="ts" name="cmsCategory" setup>

import {defineAsyncComponent, onMounted, reactive, ref} from "vue";
import {useCategoryApi} from "/@/api/cms/category";
import {ElMessage, ElMessageBox} from "element-plus";
import {Picture as IconPicture} from '@element-plus/icons-vue'

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
  const {records, total} = await categoryApi.findPage(state.tableData.param).finally(() => {
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

<style lang="scss" scoped>
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
