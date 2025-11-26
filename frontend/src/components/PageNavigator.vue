<script setup lang="ts">
import { ref, computed, watch } from "vue";
import { useRouter, useRoute } from "vue-router";
import { PlayIcon } from "@heroicons/vue/24/solid";

const router = useRouter();
const route = useRoute();

// all paths
const pages = ["municipalityData", "electionData", "national-results"];

//
const currentIndex = ref(
  pages.indexOf(route.path.replace("/", "")) // "/municipality" → "municipality"
);

//change index if path changes
watch(
  () => route.path,
  (newPath) => {
    const clean = newPath.replace("/", "");
    currentIndex.value = pages.indexOf(clean);
  }
);

// left function


function goLeft() {
  currentIndex.value =
    (currentIndex.value - 1 + pages.length) % pages.length;

  router.push(`/${pages[currentIndex.value]}`);
}

// Rechts
function goRight() {
  currentIndex.value = (currentIndex.value + 1) % pages.length;
  router.push(`/${pages[currentIndex.value]}`);
}

// title changes when path changes
const title = computed(() => {
  const map: Record<string, string> = {
    municipalityData: "Gemeente Uitslagen",
    electionData: "Kieskring Uitslagen",
    "national-results": "Nationale Uitslagen",
  };

  return map[pages[currentIndex.value]];
});
</script>


<template>
  <div class="flex flex-col items-center justify-center gap-2 text-white text-3xl font-bold">

    <!-- NAV BUTTONS -->
    <div class="flex items-center gap-4">
      <button @click="goLeft" class="p-2 rounded hover:bg-purple-700 active:bg-purple-800 transition-colors">
        <PlayIcon class="w-8 h-6 rotate-180" />
      </button>

      <span>{{ title }}</span>


      <button @click="goRight" class="p-2 rounded hover:bg-purple-700 active:bg-purple-800 transition-colors">
        <PlayIcon class="w-8 h-6" />
      </button>
    </div>
    <p class="text-xl opacity-90">Tweede Kamerverkiezingen</p>

    <!-- STEP INDICATOR -->
    <div class="flex gap-2 justify-center mt-1">
      <span
        v-for="(page, index) in pages"
        :key="index"
        class="block rounded-full transition-all duration-300"
        :class="index === currentIndex
          ? 'bg-purple-500 w-3 h-3'
          : 'bg-purple-200 w-3 h-3 opacity-50'"
      ></span>
    </div>

  </div>
</template>

