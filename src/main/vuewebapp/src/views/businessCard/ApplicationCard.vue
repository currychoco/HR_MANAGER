<script setup lang="ts">
import api from '@/lib/axios'

function requestBusiCard() {
  api.post('/busi-card/request')
    .then((response) => {
      alert("명함 신청이 완료되었습니다.");
    })
    .catch((error) => {
      console.log(error);
    });
}
</script>

<template>
<div class="container body-container">
    <h3 class="title">명함 신청</h3>
    <div class="card form-group">
        <!-- TODO: 추가
        <img src="/business-card/image?cardType=ko" class="busicard" />
        <img src="/business-card/image?cardType=en" class="busicard" />
        -->
    </div>
    <div class="form-group">
        <button type="button" class="btn btn-primary btn-block" @click="requestBusiCard">명함 신청하기</button>
    </div>
</div>
</template>

<style>
img.busicard {
    width: 540px;
    height: 300px;
    border:solid black 1px;
}
</style>