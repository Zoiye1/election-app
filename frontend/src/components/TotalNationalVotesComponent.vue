<script setup lang="ts">
import { computed, defineProps } from 'vue';

const props = defineProps<{
  totalCounted: number
}>();

const formattedVotes = computed(() => {
  return props.totalCounted.toLocaleString('nl-NL');
});
</script>

<template>
  <div class="bg-white rounded-2xl shadow-md p-6 flex items-center gap-4">
    <!-- Icon -->
    <div class="bg-purple-100 rounded-xl p-3 flex-shrink-0">
      <svg xmlns="http://www.w3.org/2000/svg" class="h-8 w-8 text-purple-600" fill="none" viewBox="0 0 24 24" stroke="currentColor">
        <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M17 20h5v-2a3 3 0 00-5.356-1.857M17 20H7m10 0v-2c0-.656-.126-1.283-.356-1.857M7 20H2v-2a3 3 0 015.356-1.857M7 20v-2c0-.656.126-1.283.356-1.857m0 0a5.002 5.002 0 019.288 0M15 7a3 3 0 11-6 0 3 3 0 016 0zm6 3a2 2 0 11-4 0 2 2 0 014 0zM7 10a2 2 0 11-4 0 2 2 0 014 0z" />
      </svg>
    </div>

    <!-- Content -->
    <div class="flex-1">
      <p class="text-gray-600 font-medium text-sm mb-1">Totaal Stemmen</p>
      <p class="text-3xl font-bold text-gray-800">{{ formattedVotes }}</p>
    </div>
  </div>
</template>

<style scoped>
</style>
