<script setup lang="ts">
import { Pie } from 'vue-chartjs'
import { Chart as ChartJS, ArcElement, Tooltip, Legend } from 'chart.js'
import { computed, ref, watch } from 'vue'
import type { TopNationalParty } from '@/interfaces/IElectionData'
import { NationalPartyService } from '@/services/NationalPartyService'
import { ElectionService } from '@/services/ElectionService'

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

const chartData = computed(() => {

  const labels = parties.value.map(party => party.partyName )

  const votes = parties.value.map(party => party.votes)

  const sumPartyVotes = votes.reduce((sum, vote) => sum + vote, 0)
  const remainingVotes = totalVotes.value - sumPartyVotes

  labels.push('Overige')
  votes.push(remainingVotes)

  return {
    labels: labels,
    datasets: [{
      data: votes
    }]
  }
})
</script>

<template>
  <div></div>
</template>
