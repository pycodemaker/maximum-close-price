package com.j4afar.MaximumClosePrice;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class MaximumClosePriceDriver {
	
	public static void main(String[] args) throws Exception {
		
		// Define MapReduce Job
		Job job = new Job();
		job.setJarByClass(MaximumClosePriceDriver.class);
		job.setJobName("MaxClosePrice");
		
		// Set input and output location
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		// Set Mapper Reducer classes
		job.setMapperClass(MaximumClosePriceMapper.class);
		job.setReducerClass(MaximumClosePriceReducer.class);
		
		// Combiner (optional) 
		job.setCombinerClass(MaximumClosePriceReducer.class);	
		
		// Output types
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(FloatWritable.class);
		
		// Submit a job
		System.exit(job.waitForCompletion(true) ? 0 : 1);
		
	}
}
