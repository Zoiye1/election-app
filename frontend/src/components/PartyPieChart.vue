<script setup lang="ts">
import { Chart as ChartJS, ArcElement, Tooltip, Legend, type TooltipItem } from 'chart.js'
import { computed, ref, watch } from 'vue'
import type { TopNationalParty } from '@/interfaces/IElectionData'
import { NationalPartyService } from '@/services/NationalPartyService'
import { ElectionService } from '@/services/ElectionService'
import { Pie } from 'vue-chartjs'

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
    // Zet lege arrays zodat component niet crasht
    parties.value = []
    totalVotes.value = 0
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

  const colors = [
    '#FF6384', '#36A2EB', '#FFCE56', '#4BC0C0', '#9966FF',
    '#FF9F40', '#FF6384', '#C9CBCF', '#E7E9ED', '#36A2EB',
    '#4BC0C0', '#FFCE56', '#9966FF', '#FF9F40', '#FF6384',
    '#36A2EB', '#FFCE56', '#4BC0C0', '#9966FF', '#FF9F40',
    '#999999'
  ]

  return {
    labels,
    datasets: [{
      data: votes,
      backgroundColor: colors
    }]
  }
})

const chartOptions = {
  responsive: true,
  maintainAspectRatio: true,
  plugins: {
    legend: {
      position: 'right' as const,
    },
    tooltip: {
      callbacks: {
        label:(context: TooltipItem<'pie'>) => {
          const label = context.label || ''
          const value = context.parsed || 0
          const percentage = ((value / totalVotes.value) * 100).toFixed(1)
          return `${label}: ${value.toLocaleString()} stemmen (${percentage}%)`
        }
      }
    }
  }
}
</script>

<template>
  <div class="w-full h-96">
    <div v-if="loading" class="flex justify-center items-center h-full text-gray-600">
      Gegevens laden...
    </div>
    <div v-else-if="parties.length === 0" class="flex justify-center items-center h-full text-gray-500">
      Geen data beschikbaar voor deze verkiezing
    </div>
    <Pie
      v-else
      :data="chartData"
      :options="chartOptions"
    />
  </div>
</template>
