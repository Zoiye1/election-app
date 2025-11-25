<script setup lang="ts">
import { ref, computed } from "vue";
import { useRouter } from "vue-router";
import { PlayIcon, PauseIcon } from '@heroicons/vue/24/solid'


const router = useRouter();

// here you put the pathname in the router
const pages = ["municipality", "/electionData", "/national-results"];

// Bepaal huidige index op basis van route
const currentIndex = ref(pages.indexOf("/electionData")); // voorbeeld

function goLeft() {
  if (currentIndex.value > 0) {
    currentIndex.value--;
    router.push(`/results/${pages[currentIndex.value]}`);
  }
}

function goRight() {
  if (currentIndex.value < pages.length - 1) {
    currentIndex.value++;
    router.push(`${pages[currentIndex.value]}`);
  }
}

const title = computed(() => {
  switch (pages[currentIndex.value]) {
    case "municipality": return "Gemeente Uitslagen";
    case "/electionData": return "Kieskring Uitslagen";
    case "/national-results": return "Nationale Uitslagen";
  }
});
</script>

<template>
  <div class="flex items-center justify-center gap-4 text-white text-3xl font-bold">

    <!-- LEFT ARROW -->
    <button @click="goLeft" class="p-2 rounded hover:bg-purple-700 active:bg-purple-800 transition-colors">
      <PlayIcon class="w-8 h-6 rotate-180"/>
    </button>

    <!-- TITLE -->
    <span>{{ title }}</span>

    <!-- RIGHT ARROW -->
    <button @click="goRight" class="p-2 rounded hover:bg-purple-700 active:bg-purple-800 transition-colors">
      <PlayIcon class="w-8 h-6"/>
    </button>

  </div>
</template>
