<script setup lang="ts">
import { defineProps } from 'vue';

defineProps<{
  totalCounted: number
}>();
</script>

<template>
  <div class="bg-blue-50 border-2 border-blue-500 rounded-lg p-8 text-center shadow-md">
    <h2 class="text-xl font-semibold text-gray-700 mb-3">
      Totaal Aantal Geldig Verklaarde Stemmen
    </h2>
    <p class="text-5xl font-bold text-blue-600">
      {{ totalCounted.toLocaleString('nl-NL') }}
    </p>
    <p class="text-sm text-gray-500 mt-2">
      Tweede Kamerverkiezingen 2023
    </p>
  </div>
</template>

<style scoped>
</style>
