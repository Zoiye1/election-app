<script setup lang="ts">

import type { PartyDetail } from '@/interfaces/IElectionData.ts'
import { computed } from 'vue'
import { getPartyLogoUrl } from '@/utils/partyLogos.ts'

const props = defineProps<{
  party: PartyDetail
  ranking: number
}>()

const emit = defineEmits<{
  close: []
}>()

const partyLogoUrl = computed(() => {
  return getPartyLogoUrl(props.party.partyName)
})

/**
 * Formats the previous election difference with + or - sign
 */
const formattedDifference = computed(() => {
  const diff = props.party.previousElectionDifference

  // No previous election data available
  if (diff === null) return null

  // Add + sign for positive numbers (negative numbers already have -)
  const sign = diff >= 0 ? '+' : ''
  return `${sign}${diff.toFixed(1)}%`
})

/**
 * Returns color class based on positive/negative difference
 */
const differenceColorClass = computed(() => {
  const diff = props.party.previousElectionDifference

  // First check for null
  if (diff === null) {
    return 'text-gray-500'
  }

  if (diff > 0) {
    return 'text-green-500'
  }

  if (diff < 0) {
    return 'text-red-500'
  }
  // Default(if diff === null text will be gray)
  return 'text-gray-500'
})

</script>

<template>
  <!-- dimmed background -->
  <div class="fixed inset-0 bg-black/50 flex items-center justify-center z-50">
    <!-- white card -->
    <div class="bg-white rounded-3xl p-6 w-full max-w-md mx-4 shadow-2xl">

      <div class="flex items-center justify-between mb-6">
        <div class="bg-purple-600 text-white px-4 py-2 rounded-full text-sm font-semibold flex items-center gap-2">
          ⚡ #{{ props.ranking }} Partij Ranking
        </div>

        <!-- return button -->
        <button @click="emit('close')" class="text-gray-500 hover:text-gray-700 flex items-center gap-1">
          ← Terug
        </button>

      </div>
      <!-- party logo-->
      <div class="flex justify-center mb-6">
        <img
          v-if="partyLogoUrl"
          :src="partyLogoUrl"
          :alt="`${props.party.partyName} logo`"
          style="height: 60px; width: auto;"
        />
        <h1 v-else class="text-3xl font-bold text-gray-800">
          {{ props.party.partyName }}
        </h1>
      </div>

    </div>
  </div>
</template>
