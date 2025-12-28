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

</script>
