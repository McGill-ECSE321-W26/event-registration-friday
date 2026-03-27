<template>
  <div class="events-view">
    <h1>Events View</h1>
    <h2>All Events</h2>
    <table id="events-table">
      <thead>
        <tr id="events-table-header">
          <th>Name</th>
          <th>Date</th>
          <th>Start Time</th>
          <th>End Time</th>
          <th>Registration Limit</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="event in events" class="events-table-row">
          <td>{{ event.name }}</td>
          <td>{{ event.date }}</td>
          <td>{{ event.startTime }}</td>
          <td>{{ event.endTime }}</td>
          <td>{{ event.registrationLimit }}</td>
        </tr>
      </tbody>
    </table>
    <h2>Create a New Event</h2>
    <div class="event-form">
      <input class="text-input" type="text" placeholder="Event Name" v-model="newEventName" />
      <input class="text-input" type="date" placeholder="Event Date" v-model="newEventDate" />
      <input class="text-input" type="time" placeholder="Start Time" v-model="newEventStartTime" />
      <input class="text-input" type="time" placeholder="End Time" v-model="newEventEndTime" />
      <input
        class="text-input"
        type="number"
        placeholder="Registration Limit"
        v-model="newEventRegistrationLimit"
      />
      <div>
        <input type="radio" name="event-type" value="online" v-model="newEventType" /> Online
        <input type="radio" name="event-type" value="inperson" v-model="newEventType" /> In Person
      </div>
      <input
        class="text-input"
        type="url"
        placeholder="Url"
        v-if="newEventType === 'online'"
        v-model="newEventUrl"
      />
      <input
        class="text-input"
        type="text"
        placeholder="Address"
        v-if="newEventType === 'inperson'"
        v-model="newEventAddress"
      />
      <div class="form-actions">
        <button class="event-button" id="createBtn" @click="createEvent()">Create Event</button>
        <button class="event-button" id="clearBtn" @click="clear()">Clear</button>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import axios from 'axios'

const events = ref([])

let newEventName = ref(null)
let newEventDate = ref(null)
let newEventStartTime = ref(null)
let newEventEndTime = ref(null)
let newEventRegistrationLimit = ref(null)
let newEventType = ref('online')
let newEventUrl = ref(null)
let newEventAddress = ref(null)

const axiosClient = axios.create({
  baseURL: 'http://localhost:8080',
})

onMounted(async () => {
  const response = await axiosClient.get('/event')
  events.value = response.data
})

async function createEvent() {
  const endpoint = newEventType.value === 'online' ? '/event/online' : '/event/inperson'
  const body = {
    name: newEventName.value,
    date: newEventDate.value,
    startTime: newEventStartTime.value + ':00',
    endTime: newEventEndTime.value + ':00',
    registrationLimit: newEventRegistrationLimit.value,
    ...(newEventType.value === 'online'
      ? { url: newEventUrl.value }
      : { address: newEventAddress.value }),
  }

  await axiosClient.post(endpoint, body)

  const response = await axiosClient.get('/event')
  events.value = response.data

  clear()
}

function clear() {
  newEventName.value = null
  newEventDate.value = null
  newEventStartTime.value = null
  newEventEndTime.value = null
  newEventRegistrationLimit.value = null
  newEventType.value = 'online'
  newEventUrl.value = null
  newEventAddress.value = null
}
</script>

<style scoped>
#events-table {
  width: 100%;
  border-collapse: collapse;
  border: 1px solid #e5e7eb;
  margin-bottom: 20px;
}
#events-table-header {
  background-color: #000;
  color: #fff;
}
#events-table-header th {
  padding: 10px 8px;
  text-align: left;
  font-weight: 600;
}
.events-table-row td {
  border: 1px solid #fff;
  padding: 8px;
}
.events-table-row:hover {
  background-color: #fff;
}
.event-button {
  color: #fff;
  border: 1px solid #fff;
  border-radius: 4px;
}

#createBtn {
  background-color: green;
}

#clearBtn {
  background-color: red;
}

.text-input {
  border: 1px solid #ccc;
  border-radius: 4px;
  padding: 6px 10px;
}

.event-form {
  display: flex;
  flex-direction: column;
  gap: 10px;
  max-width: 400px;
}

.form-actions {
  display: flex;
  gap: 8px;
}

.events-view {
  padding: 24px;
}

.event-button {
  padding: 6px 16px;
  cursor: pointer;
}
</style>