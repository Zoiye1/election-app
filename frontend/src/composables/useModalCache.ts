import { ref, watch } from 'vue'
import { useElection } from '@/composables/useElection'
import type { PartyDetail } from '@/interfaces/IElectionData'
import type { CandidateResult } from '@/interfaces/CandidateResult'

const partyCache = ref<Map<string, PartyDetail>>(new Map())
const candidateCache = ref<Map<string, CandidateResult>>(new Map())

export function useModalCache() {
  const { selectedElection } = useElection()
  
  const cacheParty = (party: PartyDetail) => {
    // Cache key bevat jaar: "2023-VVD"
    const key = `${selectedElection.value}-${party.partyName}`
    partyCache.value.set(key, party)
    console.log(`💾 Cached party: ${key}`)
  }
  
  const getCachedParty = (partyName: string): PartyDetail | undefined => {
    const key = `${selectedElection.value}-${partyName}`
    const cached = partyCache.value.get(key)
    if (cached) {
      console.log(`📦 Cache hit: ${key}`)
    }
    return cached
  }
  
  const cacheCandidate = (candidate: CandidateResult) => {
    // Cache key bevat jaar: "2023-12345"
    const key = `${selectedElection.value}-${candidate.id}`
    candidateCache.value.set(key, candidate)
    console.log(`💾 Cached candidate: ${key}`)
  }
  
  const getCachedCandidate = (candidateId: number): CandidateResult | undefined => {
    const key = `${selectedElection.value}-${candidateId}`
    const cached = candidateCache.value.get(key)
    if (cached) {
      console.log(`📦 Cache hit: ${key}`)
    }
    return cached
  }
  
  return {
    cacheParty,
    getCachedParty,
    cacheCandidate,
    getCachedCandidate
  }
}
