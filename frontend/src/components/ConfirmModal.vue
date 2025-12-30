<script setup lang="ts">
interface Props {
  title?: string
  message: string
  confirmText?: string
  cancelText?: string
  confirmClass?: string
}

withDefaults(defineProps<Props>(), {
  title: 'Bevestiging vereist',
  confirmText: 'Verwijderen',
  cancelText: 'Annuleren',
  confirmClass: 'bg-red-600 hover:bg-red-700'
})

const emit = defineEmits<{
  confirm: []
  cancel: []
}>()
</script>

<template>
  <!-- Backdrop -->
  <div
    class="fixed inset-0 bg-black/50 backdrop-blur-sm z-50 flex items-center justify-center p-4"
    @click.self="emit('cancel')"
  >
    <!-- Modal -->
    <div
      class="bg-white rounded-2xl shadow-2xl max-w-md w-full p-6 transform transition-all"
      @click.stop
    >
      <!-- Icon -->
      <div class="flex justify-center mb-4">
        <div class="w-16 h-16 bg-red-100 rounded-full flex items-center justify-center">
          <svg
            xmlns="http://www.w3.org/2000/svg"
            class="h-8 w-8 text-red-600"
            fill="none"
            viewBox="0 0 24 24"
            stroke="currentColor"
          >
            <path
              stroke-linecap="round"
              stroke-linejoin="round"
              stroke-width="2"
              d="M12 9v2m0 4h.01m-6.938 4h13.856c1.54 0 2.502-1.667 1.732-2.5L13.732 4c-.77-.833-1.964-.833-2.732 0L3.732 16.5c-.77.833.192 2.5 1.732 2.5z"
            />
          </svg>
        </div>
      </div>

      <!-- Title -->
      <h3 class="text-xl font-bold text-gray-900 text-center mb-2">
        {{ title }}
      </h3>

      <!-- Message -->
      <p class="text-gray-600 text-center mb-6">
        {{ message }}
      </p>

      <!-- Buttons -->
      <div class="flex gap-3">
        <button
          @click="emit('cancel')"
          class="flex-1 px-4 py-3 border-2 border-gray-300 text-gray-700 font-semibold rounded-lg hover:bg-gray-50 transition-all duration-300"
        >
          {{ cancelText }}
        </button>
        <button
          @click="emit('confirm')"
          :class="[
            'flex-1 px-4 py-3 text-white font-semibold rounded-lg transition-all duration-300 hover:-translate-y-0.5 shadow-lg',
            confirmClass
          ]"
        >
          {{ confirmText }}
        </button>
      </div>
    </div>
  </div>
</template>
