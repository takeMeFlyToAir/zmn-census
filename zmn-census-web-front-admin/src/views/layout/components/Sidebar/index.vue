<template>
  <div class="has-logo">
    <logo :collapse="isCollapse" />
    <scroll-bar>
      <el-menu mode="vertical" unique-opened :default-active="$route.path" :collapse="isCollapse"
               background-color="#304156" text-color="#fff" active-text-color="#409EFF">
        <sidebar-item :routes="permission_routers"></sidebar-item>
      </el-menu>
    </scroll-bar>
  </div>
</template>

<script>
  import {mapGetters} from 'vuex'
  import Logo from './Logo'
  import SidebarItem from './SidebarItem'
  import ScrollBar from '@/components/ScrollBar'

  export default {
    components: {SidebarItem, ScrollBar,Logo},
    computed: {
      ...mapGetters([
        'permission_routers',
        'sidebar'
      ]),
      isCollapse() {
        return !this.sidebar.opened
      }
    }
  }
</script>
