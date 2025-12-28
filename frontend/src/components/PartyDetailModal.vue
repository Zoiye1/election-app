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

</script>
