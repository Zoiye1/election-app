<script setup lang="ts">
import { computed, defineProps, ref, onMounted, watch } from 'vue';
import { ElectionService } from '@/services/ElectionService';
import type { ConstituencyPartyVotes } from '@/interfaces/IElectionData';


const props = defineProps<{
  name: string
  election: string
}>();


const percentage = ref<number>(0);
onMounted(async () => {
  const constituencyVotesPercentage = await ElectionService.getConstituencyVotesPercentage(props.election, props.name);
  percentage.value = constituencyVotesPercentage;
});


const formattedVotes = computed(() => {
  return percentage.value.toLocaleString("nl-NL", { minimumFractionDigits: 1, maximumFractionDigits: 1 });
});

watch(props, async (newValue) => {

    const election = await ElectionService.getConstituencyVotesPercentage(newValue.election, newValue.name);
    percentage.value = election;


});
</script>

<template>
  <div class="bg-white rounded-2xl shadow-md p-5 flex items-center gap-4">
    <!-- Icon -->
    <div class="bg-purple-100 rounded-xl p-3 flex-shrink-0">
      <svg xmlns="http://www.w3.org/2000/svg" class="h-8 w-8 text-purple-600" fill="none" viewBox="0 0 24 24" stroke="currentColor">
        <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M17 20h5v-2a3 3 0 00-5.356-1.857M17 20H7m10 0v-2c0-.656-.126-1.283-.356-1.857M7 20H2v-2a3 3 0 015.356-1.857M7 20v-2c0-.656.126-1.283.356-1.857m0 0a5.002 5.002 0 019.288 0M15 7a3 3 0 11-6 0 3 3 0 016 0zm6 3a2 2 0 11-4 0 2 2 0 014 0zM7 10a2 2 0 11-4 0 2 2 0 014 0z" />
      </svg>
    </div>

    <!-- Content -->
    <div class="flex-1">
      <p class="text-gray-600 font-medium text-sm mb-1">Percentage van totaal stemmen</p>
      <p class="text-2xl font-bold text-gray-800">
        <span class="text-purple-600">{{ formattedVotes }}%</span>
        van de stemmers komt uit
        {{ props.name }}
      </p>
    </div>
  </div>
</template>

<style scoped>
</style>
