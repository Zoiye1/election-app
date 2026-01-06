<script setup lang="ts">
import { Pie } from 'vue-chartjs'
import { Chart as ChartJS, ArcElement, Tooltip, Legend } from 'chart.js'
import type { TopNationalParty } from '@/interfaces/IElectionData.ts'
import { ref, watch } from 'vue'
import { ElectionService } from '@/services/ElectionService.ts'
import { NationalPartyService } from '@/services/NationalPartyService.ts'

ChartJS.register(ArcElement, Tooltip, Legend)

const props = defineProps<{
  electionId: string
}>()

// State
const parties = ref<TopNationalParty[]>([])
const totalVotes = ref<number>(0)
const loading = ref<boolean>(true)

async function fetchData() {
  loading.value = true
  try {
    parties.value = await NationalPartyService.getTopParties(props.electionId)
    totalVotes.value = await ElectionService.getTotalVotes(props.electionId)
  } catch (error) {
    console.error('Failed to fetch data:', error)
  } finally {
    loading.value = false
  }
}
// check for election selector changes
watch(
  () => props.electionId,
  () => fetchData(),
  { immediate: true },
)
</script>

<template>
  <div></div>
</template>
