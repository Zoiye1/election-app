# ElectionSelector Component

Quick documentation for using the election year selector in your views.

## What it does

Shows buttons for 2021, 2023, 2025. When a user clicks a year, **all views** see the change automatically. No need to pass state around!

## Files

- `src/components/ElectionSelector.vue` - The component
- `src/composables/useElection.ts` - Shared state

---

## How to use it

### In your view:

```vue
<script setup lang="ts">
import { useElection } from '@/composables/useElection'
import ElectionSelector from '@/components/ElectionSelector.vue'

const { selectedElection } = useElection()
</script>

<template>
  <ElectionSelector />
  <YourComponent :electionId="selectedElection" />
</template>
```

Done! Your view now has the selector and can pass `selectedElection` to child components.

---

## In your child component:

Your component needs to accept `electionId` as a prop and watch for changes:

```vue
<script setup lang="ts">
import { watch, ref } from 'vue'

const props = defineProps<{
  electionId: string
}>()

const results = ref([])

// Fetch new data when election changes
watch(
  () => props.electionId,
  async (newId) => {
    results.value = await fetchResults(newId)
  },
  { immediate: true }
)
</script>
```

**New to props?** Check [Vue Props docs](https://vuejs.org/guide/components/props.html)

---

## Questions?

Check the code or ask Matisse!