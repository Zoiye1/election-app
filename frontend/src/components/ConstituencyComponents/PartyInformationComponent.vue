<script setup lang="ts">
import { computed, onMounted, ref, watch } from "vue";
import { ConstituencyService } from "@/services/ConstituencyService";

const props = defineProps<{
  name: string;
  election: string;
  party: string | null;
}>();

const isEmpty = computed(() => props.party === null);
const error = ref<boolean>(false)
const errorMessage = `Er is geen data beschikbaar voor deze partij in dit jaar 😬 `
const emptyMessage = "Selecteer een partij";

const percentage = ref<number>(0);
onMounted(async () => {
  try {
    const constituencyVotesPercentage = await ConstituencyService.getConstituencyVotesPercentage(props.election, props.name, props.party);
    percentage.value = constituencyVotesPercentage;

  }
  catch (e) {

    percentage.value = 0;

  }

});

watch(props, async (newValue) => {

  try {
    const constituencyVotesPercentage = await ConstituencyService.getConstituencyVotesPercentage(newValue.election, newValue.name, newValue.party);
    percentage.value = constituencyVotesPercentage;
    error.value = false;
  }
  catch (e) {

   error.value = true;
  }

});

const formattedPercentage = computed(() => {
  if (percentage.value < 0.1) {
    return "minder dan 0,1%";
  }
  return `${percentage.value}%`;
})
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
    <div v-if="isEmpty" class="text-xl font-bold text-purple-600">
      {{ emptyMessage }}
    </div>

    <div v-else-if="error" class="bg-red-50 text-red-600 border border-red-200 rounded-lg px-4 py-3 mb-4">
      {{ errorMessage }}
    </div>
    <div v-else class="bg-white shadow-md p-6 rounded-xl border border-purple-100">
      <p class="text-gray-800 text-sm mb-2">Aandeel binnen de kieskring</p>

      <p class="text-gray-700 text-lg font-bold">
        Deze partij behaalde
        <span class="text-purple-600 font-bold">{{formattedPercentage}}</span>
        van alle uitgebrachte stemmen in deze kieskring.
      </p>

    </div>



  </div>
</template>
