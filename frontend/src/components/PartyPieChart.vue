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
  layout: {
    padding: {
      left: 20,
      right: 20,
      top: 10,
      bottom: 10
    }
  },
  plugins: {
    legend: {
      position: 'bottom' as const,
      align: 'center' as const,
      labels: {
        padding: 15,
        font: {
          size: 12
        }
      }
    },
    tooltip: {
      backgroundColor: 'rgba(0, 0, 0, 0.8)',
      padding: 12,
      callbacks: {
        label: (context: TooltipItem<'pie'>) => {
          const label = context.label || ''
          const value = context.parsed || 0
          const percentage = ((value / totalVotes.value) * 100).toFixed(1)
          return `${label}: ${value.toLocaleString('nl-NL')} stemmen (${percentage}%)`
        }
      }
    }
  }
}
</script>

<template>
    <div class="w-full flex justify-center">
      <div v-if="loading" class="flex justify-center items-center h-64 text-gray-600">
        <div class="text-center">
          <div class="animate-spin rounded-full h-12 w-12 border-b-2 border-purple-600 mx-auto mb-4"></div>
          <p>Gegevens laden...</p>
        </div>
      </div>

      <div v-else-if="parties.length === 0" class="flex justify-center items-center h-64 text-gray-500">
        <div class="text-center">
          <p class="text-xl">📊</p>
          <p>Geen data beschikbaar voor deze verkiezing</p>
        </div>
      </div>

      <div v-else class="w-full max-w-lg mx-auto">
        <Pie :data="chartData" :options="chartOptions" />
      </div>
    </div>
</template>
