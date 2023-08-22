<template>
  <div class="system-dic-container layout-padding">
    <el-card class="layout-padding-auto" shadow="hover">
      <div class="mb15">
        <el-input v-model="state.tableData.param.search" clearable placeholder="请输入标题"
                  size="default" style="max-width: 180px"></el-input>
        <el-button class="ml10" size="default" type="primary" @click="onSearch">
          <el-icon>
            <ele-Search/>
          </el-icon>
          查询
        </el-button>
        <el-button class="ml10" size="default" type="success" @click="onOpenAdd('add')">
          <el-icon>
            <ele-FolderAdd/>
          </el-icon>
          新增文章
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
        <el-table-column label="标题" prop="title" show-overflow-tooltip></el-table-column>
        <el-table-column label="作者" prop="createUser"></el-table-column>
        <el-table-column label="状态" prop="createUser">
          <template #default="scope">
            <el-tag v-if="scope.row.status === 2" size="mini" type="success">发布</el-tag>
            <el-tag v-else size="mini" type="warning">草稿</el-tag>
          </template>
        </el-table-column>
        <el-table-column label="分类" prop="description" show-overflow-tooltip>
          <template #default="scope">
            <el-tag v-for="item in scope.row.categories" :key="item.categoryId" size="mini" style="margin-right: 5px"
                    type="success">{{ item.name }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column label="标签" prop="postCount" show-overflow-tooltip>
          <template #default="scope">
            <el-tag v-for="item in scope.row.tags" :key="item.tagId" size="mini" style="margin-right: 5px"
                    type="success">{{ item.name }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column label="操作" width="130">
          <template #default="scope">
            <el-button size="small" text type="primary" @click="onOpenEdit('edit', scope.row)">修改</el-button>
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
    </el-card>
  </div>
</template>

<script lang="ts" name="cmsPost" setup>

import {onMounted, reactive} from "vue";
import {usePostApi} from "/@/api/cms/post";
import {ElMessage, ElMessageBox} from "element-plus";
import {Picture as IconPicture} from '@element-plus/icons-vue'
import {useRouter} from 'vue-router';

const router = useRouter();

const postApi = usePostApi()

// 定义变量内容
const state = reactive({
  tableData: {
    param: {
      pageNo: 1,
      pageSize: 10,
      search: ''
    },
    data: [],
    total: 0,
    loading: false
  }
})

// 初始化表格数据
const getTableData = async () => {
  state.tableData.loading = true;
  const {records, total} = await postApi.findPage(state.tableData.param).finally(() => {
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
const onOpenAdd = (type: string) => {
  router.push({name: 'cmsPostEdit', query: {type}})
}
const onOpenEdit = (type: string, row: any) => {
  router.push({name: 'cmsPostEdit', query: {type, postId: row.postId}})
}
// 删除分类
const onRowDel = (row: any) => {
  ElMessageBox.confirm(`此操作将永久删除文章：“${row.title}”，是否继续?`, '提示', {
    confirmButtonText: '确认',
    cancelButtonText: '取消',
    type: 'warning',
  }).then(async () => {
    await postApi.delete(row.postId);
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
</style>
