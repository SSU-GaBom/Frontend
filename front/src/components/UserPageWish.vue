<template>
  <v-card
    class="mx-auto"
    tile
  >
    <v-sheet
      class="mx-auto"
      elevation="8"
      max-width="800"
    >
      <v-list-item one-line>
        <v-list-item-content>
          <v-list-item-title class="headline text-left">찜 목록</v-list-item-title>
        </v-list-item-content>
      </v-list-item>
      <v-slide-group
        v-model="model"
        class="pa-4"
        active-class="success"
        show-arrows
      >
        <v-slide-item
          v-for="n in 15"
          :key="n"
          v-slot="{ active, toggle }"
        >
          <v-card
            :color="active ? undefined : 'grey lighten-1'"
            class="ma-4"
            height="100"
            width="100"
            @click="toggle"
          >
            <v-img
              :src="`https://picsum.photos/500/300?image=${n * 5 + 10}`"
              :lazy-src="`https://picsum.photos/10/6?image=${n * 5 + 10}`"
              aspect-ratio="1"
              class="grey lighten-2"
            ></v-img>
            <v-row
              class="fill-height"
              align="center"
              justify="center"
            >
              <v-scale-transition>
                <v-icon
                  v-if="active"
                  color="white"
                  size="24"
                  v-text="'mdi-close-circle-outline'"
                ></v-icon>
              </v-scale-transition>
            </v-row>
          </v-card>
        </v-slide-item>
      </v-slide-group>
    </v-sheet>
  </v-card>
</template>

<script>
  export default {
    data: () => ({
      model: null,
    }),
  }
</script>

<style>
</style>