<script setup lang="ts">
import { computed } from "vue";

const props = defineProps<{
  name: string;
  election: string;
  party: string | null;
}>();

const isEmpty = computed(() => props.party === null);

const emptyMessage = "Selecteer een partij";
</script>

<template>
  <div class="max-w-4xl mx-auto p-6 bg-white rounded-xl shadow-md">

    <!-- Title -->
    <div class="flex items-center justify-between mb-6">
      <h2 class="text-2xl font-bold text-gray-800 flex items-center gap-2">
        🔎 Partij Informatie
        <span v-if="!isEmpty" class="text-purple-600">
          {{ props.party }}
        </span>
      </h2>
    </div>

    <!-- If there's no selected party -->
    <div v-if="isEmpty" class="text-xl font-semibold text-purple-600">
      {{ emptyMessage }}
    </div>

    <div v-else>
      <p class="text-gray-700">Informatie over {{ props.party }} wordt hier geladen...</p>
    </div>

  </div>
</template>
