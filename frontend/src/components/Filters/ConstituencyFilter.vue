<script setup lang="ts">
import { ref } from 'vue';

// Two-way binding met parent
const model = defineModel<string>('modelValue');

// Voor openen/sluiten
const open = ref(false);

const constituencies = [
  'Amsterdam', 'Arnhem', 'Assen', 'Bonaire', 'Den Helder', 'Dordrecht',
  'Groningen', 'Haarlem', 'Leeuwarden', 'Leiden', 'Lelystad',
  'Maastricht', 'Middelburg', 'Nijmegen', 'Rotterdam', 'Tilburg',
  'Utrecht', "'s-Hertogenbosch", "'s-Gravenhage", 'Zwolle'
];

function toggle() {
  open.value = !open.value;
}

function select(c: string) {
  model.value = c;
  open.value = false;
}
</script>

<template>
  <div class="relative w-64 mx-auto text-gray-700">
    <button
      @click="toggle"
      class="w-full flex items-center justify-between bg-white border border-gray-300 rounded-2xl px-4 py-3
             shadow-md hover:border-purple-400 focus:ring-2 focus:ring-purple-300
             focus:border-purple-500 transition-all duration-200"
    >
      <span class="font-medium">{{ model || 'Kies een kieskring' }}</span>

      <svg
        class="w-5 h-5 ml-2 text-gray-500 transition-transform duration-200"
        :class="{ 'rotate-180': open }"
        fill="none"
        stroke="currentColor"
        viewBox="0 0 24 24"
      >
        <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
              d="M19 9l-7 7-7-7" />
      </svg>
    </button>

    <transition
      enter-active-class="transition ease-out duration-200"
      enter-from-class="opacity-0 translate-y-1"
      enter-to-class="opacity-100 translate-y-0"
      leave-active-class="transition ease-in duration-150"
      leave-from-class="opacity-100 translate-y-0"
      leave-to-class="opacity-0 translate-y-1"
    >
      <ul
        v-if="open"
        class="absolute mt-2 w-full bg-white border border-gray-200 rounded-2xl shadow-lg
               max-h-48 overflow-y-auto z-20 py-2"
      >
        <li
          v-for="(city, i) in constituencies"
          :key="i"
          @click="select(city)"
          class="px-4 py-2 cursor-pointer hover:bg-purple-100 rounded-xl transition-colors"
        >
          {{ city }}
        </li>
      </ul>
    </transition>
  </div>
</template>

<style scoped>
/* optioneel: scrollbar styling */
ul::-webkit-scrollbar {
  width: 6px;
}
ul::-webkit-scrollbar-thumb {
  background: #c4b5fd; /* light purple */
  border-radius: 10px;
}
</style>
