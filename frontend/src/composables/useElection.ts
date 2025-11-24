import { ref } from 'vue'

//standard "TK2025"
const selectedElection = ref<string>('TK2025')

// elections that can be chosen from in selector
const availableElections = [
  { year: '2021', id: 'TK2021'},
  { year: '2023', id: 'TK2023'},
  { year: '2025', id: 'TK2025'},
]

export function useElection() {
  function setElection(electionId: string) {
    selectedElection.value = electionId
  }

  return {
    selectedElection,
    availableElections,
    setElection
  }
}
