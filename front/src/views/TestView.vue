<template>
  <div>
      <div>{{userName}}</div>
      <div>{{userEmail}}</div>
  </div>

</template>

<script>
import {testUser} from '../api/index'

export default {
    name: 'TestView',
    data () {
        return {
            userName : null,
            userEmail : null,

        }
    },
    methods: {
        async testUserData() {
            const response = await testUser();
            console.log(response)

        }
    },
    created() {

    },
    components: {
    },
  }
</script>