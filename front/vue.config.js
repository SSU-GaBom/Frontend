const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  outputDir: "./static",  // 빌드 타겟 디렉토리
  // css: {
  //   loaderOptions: {
  //     sass: {
  //       prependData: `
  //         @import "@/scss/main.scss";
  //       `
  //     }
  //   }
  // },
  devServer: {
    proxy: {
      '/api': {
        // '/api' 로 들어오면 포트 8081(스프링 서버)로 보낸다
        target: 'http://localhost:8081',
        changeOrigin: true // cross origin 허용
      }
    }
  },
  transpileDependencies: [
    'vuetify'
  ]
})
