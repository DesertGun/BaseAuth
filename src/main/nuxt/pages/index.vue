<template>
  <div class="container">
    <div>
      <logo />
      <h5 class="title">BaseAuth</h5>
      <h5 class="subtitle">Basic web-app authentication done right !</h5>
      <div v-if="!isLoggedIn">
        <h4 class="subtitle">
          Welcome and thank you for your interest in our setup !
        </h4>
      </div>
      <div v-else>
        <h4 class="subtitle">
          Welcome back dear {{ getUsername }} ! Thank you for logging back in !
        </h4>
      </div>
      <div v-if="!isLoggedIn">
        <div class="links">
          <b-button nuxt-link to="/register" variant="primary">
            Register
          </b-button>
          <b-button nuxt-link to="/login" variant="secondary"> Login</b-button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
import Logo from '~/components/Logo.vue'

export default {
  components: {
    Logo,
  },
  asyncData() {},
  computed: {
    ...mapGetters({
      isLoggedIn: 'authenticated/isLoggedIn',
      getUsername: 'profile/getUsername',
    }),
  },
  mounted() {},
  methods: {},
}
</script>

<style>
.container {
  margin: 0 auto;
  display: flex;
  justify-content: center;
  align-items: center;
  text-align: center;
}

.title {
  font-family: 'Quicksand', 'Source Sans Pro', -apple-system, BlinkMacSystemFont,
    'Segoe UI', Roboto, 'Helvetica Neue', Arial, sans-serif;
  display: block;
  font-weight: 300;
  font-size: 75px;
  color: #35495e;
  letter-spacing: 1px;
}

.subtitle {
  font-weight: 300;
  font-size: 42px;
  color: #526488;
  word-spacing: 5px;
}
</style>
